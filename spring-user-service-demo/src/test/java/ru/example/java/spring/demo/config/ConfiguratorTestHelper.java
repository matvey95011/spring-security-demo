package ru.example.java.spring.demo.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.listener.RetryListenerSupport;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
import ru.example.java.spring.demo.entity.User;
import ru.example.java.spring.demo.repository.UserRepository;

import java.util.Collections;
import java.util.UUID;

public abstract class ConfiguratorTestHelper {

    protected static final ObjectMapper MAPPER;

    private static final int RETRIES = 2;
    private static final int BACK_OFF_PERIOD = 0;

    private static final RetryTemplate RETRY_TEMPLATE = configRetryTemplate();

    static {
        MAPPER = new ObjectMapper();
        MAPPER.registerModule(new JavaTimeModule());
    }

    private UserRepository userRepository;

    protected void config(TestBeanContext context) {
        userRepository = context.userRepository();
    }

    protected User createUser(String username) {
        return createUser(UUID.randomUUID(), username, "password");
    }

    protected User createUser(
        UUID id,
        String username,
        String password
    ) {
        User user = new User();

        user.setId(id);
        user.setUsername(username);
        user.setPassword(password);

        return save(userRepository, user);
    }



    private static RetryTemplate configRetryTemplate() {
        var retryPolicy = new SimpleRetryPolicy(
                RETRIES, Collections.singletonMap(DataIntegrityViolationException.class, Boolean.TRUE)
        );
        var backOffPolicy = new FixedBackOffPolicy();
        backOffPolicy.setBackOffPeriod(BACK_OFF_PERIOD);

        var retryTemplate = new RetryTemplate();
        retryTemplate.setRetryPolicy(retryPolicy);
        retryTemplate.setBackOffPolicy(backOffPolicy);
        retryTemplate.registerListener(new CustomRetryListener());

        return retryTemplate;
    }

    @Slf4j
    private static class CustomRetryListener extends RetryListenerSupport {

        @Override
        public <T, E extends Throwable> void onError(
                RetryContext context,
                RetryCallback<T, E> callback,
                Throwable throwable) {
            log.warn("Retrying...started current retry count is {} Retry Method {}",
                    context.getRetryCount(), context.getAttribute("context.name")
            );
            super.onError(context, callback, throwable);
        }
    }

    protected <T> T save(JpaRepository<T, UUID> repository, T entity) {
        return RETRY_TEMPLATE.execute(context -> repository.save(entity));
    }

}
