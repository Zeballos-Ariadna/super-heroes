package technical.test.superheroes.Exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import technical.test.superheroes.Commons.Problem;

@Getter
public class ApiException extends RuntimeException {
    private Problem problem;
    private HttpStatus status;
    public ApiException(Problem problem, int status) {
        this.problem = problem;
        this.status = HttpStatus.resolve(status);
    }
}
