import { HTTP_INTERCEPTORS, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { LoginService } from "./login.service";

// const TOKEN_HEADER = 'Authorization'; //this is key

@Injectable()
export class AuthInterceptor implements HttpInterceptor{

    /**
     *
     */
    constructor(private login:LoginService) {}

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        // throw new Error('Method Not Implemented !')
        // add the jwt token from local storage
        let authReq=req;
        const token=this.login.getToken();
        console.log("inside intercepter")
        console.log("token",token)
        if(token != null){
            authReq=authReq.clone({
                setHeaders:{ Authorization: `Bearer ${token}` },
            });
        }
        return next.handle(authReq)
    }
}

// export const authInterceptorProvidor=[
//     {
//         provide:HTTP_INTERCEPTORS,
//         useClass:AuthInterceptor,
//         multi:true,
//     },
// ];
