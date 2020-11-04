import axios, { Method } from 'axios';
import qs from 'qs';
import { CLIENT_ID, CLIENT_SECRET } from './auth';

type RequestParams = {
    method?: Method;
    url: string;
    data?: object | string;
    params?: object;
    headers?: Object;
}
type LoginData = {
    username: string;
    password: string;
}
const BASE_URL = 'http://localhost:8080'
// const BASE_URL = 'http://localhost:3000' //Sem CORS   //"proxy": "http://localhost:8080", PACKJSON


export const makeRequest = ({ method = 'GET', url, data, params, headers }: RequestParams) => {
    return axios({
        method,
        url: `${BASE_URL}${url}`,
        data,
        params,
        headers
    });
}
export const makeLogin = (loginData: LoginData) => {
    const token = `${CLIENT_ID}:${CLIENT_SECRET}`;
    const headers = {
        Authorization: `Basic ${window.btoa(token)}`,
        'Content-Type': `application/x-www-form-urlencoded`
    }

    const payload = qs.stringify({ ...loginData, grant_type: 'password' });
    return makeRequest({ url: '/oauth/token', data: payload, method: 'POST', headers });
    //'/oauth/token'
    //username=maria@gmail.com&password=123456&grant_type=password
}   
