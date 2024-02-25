package com.quiz.quizapp.filter;

import com.quiz.quizapp.dao.AuditDao;
import com.quiz.quizapp.encryption.EncryptionUtil;
import com.quiz.quizapp.model.Audit;
import com.quiz.quizapp.util.BasicUtility;
import com.quiz.quizapp.util.DefaultValuesPopulator;
import com.quiz.quizapp.util.HeadersUtility;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;
import java.io.IOException;

/**
 * The type Audit filter.
 */
@Component
@Order(2)
public class AuditFilter implements Filter {

    @Value("${app.security.secret-key}")
    private String secretKey;
    private final AuditDao auditDao;
    private static final Logger logger = LoggerFactory.getLogger(AuditFilter.class);
    private final EncryptionUtil encryptionUtil;


    /**
     * Instantiates a new Audit filter.
     *
     * @param auditDao       the audit dao
     * @param encryptionUtil the encryption util
     */
    public AuditFilter(AuditDao auditDao, EncryptionUtil encryptionUtil) {
        this.auditDao = auditDao;
        this.encryptionUtil = encryptionUtil;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);
        try {
            filterChain.doFilter(requestWrapper, responseWrapper);
        } catch (ServletException | IOException e) {
            logger.error(e.getMessage());
        } finally {
            saveAuditData(requestWrapper, responseWrapper);
            try {
                responseWrapper.copyBodyToResponse();
                response.flushBuffer();
            } catch (IOException e) {
                logger.error(e.getMessage());
            }
        }
    }

    private void saveAuditData(ContentCachingRequestWrapper requestWrapper, ContentCachingResponseWrapper responseWrapper) {
        try {
            int statusCode = responseWrapper.getStatus();
            String responseHeaders = HeadersUtility.extractResponseHeaders(responseWrapper);
            String responseBody = new String(responseWrapper.getContentAsByteArray());
            String requestBody = requestWrapper.getContentAsString();
            String requestHeaders = HeadersUtility.extractRequestHeaders(requestWrapper);
            String uid = BasicUtility.readSpecificProperty(responseBody, "$.uid");

            Audit audit = new Audit();
            audit.setTimestamp(DefaultValuesPopulator.getCurrentTimestamp());
            audit.setMethodName(requestWrapper.getMethod());
            audit.setRequest(encryptionUtil.encrypt(requestHeaders + "\n" + "Request Body: " + requestBody, secretKey));
            audit.setResponse(encryptionUtil.encrypt(responseHeaders + "\n" + "Response Body: " + responseBody, secretKey));
            audit.setStatus(String.valueOf(statusCode));
            audit.setUri(requestWrapper.getRequestURI());
            audit.setUid(uid);
            auditDao.save(audit);
        } catch (Exception e) {
            logger.error("Error saving audit data: {}", e.getMessage());
        }
    }
}
