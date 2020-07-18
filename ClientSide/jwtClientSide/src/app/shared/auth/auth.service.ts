import {UserToken} from '../../models/user-token.model';
import {catchError, map, retry} from 'rxjs/operators';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {Injectable} from '@angular/core';
import * as jwtDecode from 'jwt-decode';
import {throwError} from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class AuthService {

  
  private static API = 'http://localhost:8080';
  private static EMAIL_CHECK_ENDPOINT = 'http://localhost:8080/rest/users/search/existsByEmail';

  
  constructor(private http: HttpClient) {
  }

  
  isValidToken(expiration: number) {
    const now = new Date().getTime();
    return expiration > now;
  }

  
  isAuthenticated() {
    const userToken: UserToken = JSON.parse(localStorage.getItem('userToken'));
    
    if (userToken != null && this.isValidToken(userToken.exp)) {
      return true;
    } else {
      this.logout();
      return false;
    }
  }


  getAuthenticatedUser() {
    const userToken: UserToken = JSON.parse(localStorage.getItem('userToken'));
    if (userToken != null) {
      return userToken;
    } else {
      return null;
    }
  }

 
  login(email: string, password: string) {
    return this.http
      .post<{ token: string }>(`${AuthService.API}/authenticate`, {
        email,
        password
      }, {headers: new HttpHeaders().append('Authorization', '')})
      .pipe(
        map(userData => {

          const userToken = this.decodeToken(userData['jwtToken']);
          localStorage.setItem('userToken', JSON.stringify(userToken));
          return userToken;
        })
      );
  }

  logout() {
    localStorage.clear();
  }

 
  async hasRole(roleName: string) {
    const userToken: UserToken = JSON.parse(localStorage.getItem('userToken'));
    return (
      userToken.roles.find(role => role.authority === roleName) != null
    );
  }

  
  isAdmin() {
    const userToken: UserToken = JSON.parse(localStorage.getItem('userToken'));
    return (
      userToken.roles.find(role => role.authority === 'ROLE_ADMIN') != null
    );
  }

  
  decodeToken(token: string) {
    const decoded = jwtDecode(token);
    const userToken = new UserToken();
    userToken.bearerToken = 'Bearer ' + token;
    userToken.token = token;
    userToken.email = decoded.sub;
    userToken.roles = decoded.roles;
    userToken.exp = Number(decoded.exp * 1000);
    return userToken;
  }

 
  checkEmailValidity(email: string) {
    return this.http.get<boolean>(AuthService.EMAIL_CHECK_ENDPOINT, {params: new HttpParams().append('email', email)}).pipe(
      map((exists) => {
        if (exists) {
          if (!this.isAuthenticated()) {
            return {isEmailAlreadyUsed: exists};
          } else {
            if (this.getAuthenticatedUser().email !== email) {
              return {isEmailAlreadyUsed: exists};
            } else {
              return null;
            }
          }
        } else {
          return null;
        }
      }),
      retry(3),
      catchError(this.handleEmailValidityError)
    );
  }

  
  checkEmailExisting(email: string) {
    return this.http.get<boolean>(AuthService.EMAIL_CHECK_ENDPOINT, {params: new HttpParams().append('email', email)}).pipe(
      map((exists) => {
        if (exists) {
          return null;
        } else {
          return {isEmailDoesNotExist: true};
        }
      }),
      retry(3),
      catchError(this.handleEmailValidityError)
    );
  }

  
  handleEmailValidityError(error) {
    return throwError(error);
  }
}