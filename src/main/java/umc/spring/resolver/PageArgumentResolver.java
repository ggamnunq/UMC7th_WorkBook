package umc.spring.resolver;

import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.PageHandler;
import umc.spring.validation.annotation.PageValid;

@Component
@RequiredArgsConstructor
public class PageArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(PageValid.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        PageValid annotation = parameter.getParameterAnnotation(PageValid.class);
        String paramName = parameter.getParameterName();
        String paramValue = webRequest.getParameter(paramName);


        // 빈 값일 경우 첫 페이지 반환
        if (paramValue == null || paramValue.isEmpty()) {
            return 0;
        }

        // 1보다 작을 경우 예외 발생
        if (Integer.parseInt(paramValue) < 1) {
            throw new PageHandler(ErrorStatus.PAGE_LESS_THAN_ONE);
        }

        int page = Integer.parseInt(paramValue);
        return page - 1;
    }


}
