import { HttpRequest, HttpEvent, HttpInterceptorFn, HttpHandlerFn, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';

export const loggingInterceptorResponse: HttpInterceptorFn = (req: HttpRequest<any>, next: HttpHandlerFn): Observable<HttpEvent<any>> => {
    return next(req).pipe(
        tap({
            next: (event) => {
                if (event instanceof HttpResponse) {
                    console.log('Response body:', event);
                }
            },
            error: err => {
                console.error('Error:', err);
            }
        })
    );
};
