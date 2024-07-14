package com.beyond.aop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CharacterAspect {
	/*
	 * 포인트 커트 표현식
	 *   - 스프링 AOP에서 포인트 커트는 AspectJ의 포인트 커트 표현식을 이용해서 정의한다.
	 *   - 스프링 AOP에서 지원되는 AspectJ의 포인트 커트 표현식
	 *     execution([접근제한자] 리턴타입 클래스이름.메소드이름([파라미터, ...]))
	 *      - 메소드 실행에 대한 표현식이다.
	 *      - * : 모든 값을 표현한다.
	 *      - .. : 0개 이상을 의미한다.
	 *     args()
	 *      - 대상 메소드에 전달되는 매개값을 어드바이스에 전달하기 위한 표현식이다.
	 *     bean()
	 *      - 어드바이스를 적용할 빈의 ID를 지정하는 표현식이다.
	 *     @annotation()
	 *      - 주어진 어노테이션을 갖는 조인포인트에 어드바이스가 적용된다.
	 */
	@Pointcut("execution(* com.beyond.aop.character.Character.quest(..)) && args(questName)")
	public void questPointcut(String questName) {
	}
	
//	@Before("execution(* com.beyond.aop.character.Character.quest(..))")
//	@Before("questPointcut(questName)")
	public void beforeQuest(String questName) {
		// 퀘스트를 수행하기 전에 필요한 작업들 수행 
		
		System.out.println(questName + " 퀘스트 준비 중..");
	}
	
//	@After("execution(* com.beyond.aop.character.Character.quest(..))")
	public void afterQuest() {
		// 퀘스트 수행 결과에 상관없이 필요한 작업들 수행
		
		System.out.println("퀘스트 수행 완료..");
	}
	
//	@AfterReturning(
//		pointcut = "questPointcut(questName)",
//		returning = "result"
//	)
	public void success(String questName, String result) {
		System.out.println("result : " + result);
		System.out.println(questName + " 퀘스트 수행 완료..");
	}
	
//	@AfterThrowing(
//		pointcut = "questPointcut(questName)",
//		throwing = "exception"
//	)
	public void fail(String questName, Exception exception) {
		System.out.println("message : " + exception.getMessage());
		System.out.println(questName + " 퀘스트 수행 중 에러가 발생했습니다..");
	}
	
	@Around("execution(* com.beyond.aop.character.Character.quest(..))") 
    // 도토리 줍기 이후 프록시처럼 가로채는데 around가 실행되어야 다음이 실행될 수 있음.
    public String around(ProceedingJoinPoint joinPoint) {
		String result = null;
        String questName = "[" + (String)joinPoint.getArgs()[0] + "]"; // 리턴타입이 배열이라서 []를 붙여서 0번 인텍스를 가져온다.

        try {
            // before 어드바이스에 대한 기능을 수행
            System.out.println(questName + " 퀘스트 준비 중..");

            // 타겟 객체의 메소드를 실행시킨다.
//            joinPoint.proceed(); // 실제 조인 포인트의 quest라는 메소드가 실행되도록 해줌.

            // 타겟 객체의 메소드에 리턴값이 있는 경우
            result = (String)joinPoint.proceed(new Object[] { questName });

            // after-returning 어드바이스에 대한 기능을 수행
            System.out.println(result);
            System.out.println(questName + " 퀘스트 수행 완료..");

        } catch (Throwable e) {
            // after-throwing 어드바이스에 대한 기능을 수행
        	System.out.println(e.getMessage());
            System.out.println(questName + " 퀘스트 수행 중 에러가 발생했습니다..");
        }
        
        return  result;
    }
}