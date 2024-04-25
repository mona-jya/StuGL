import axios from 'axios'
axios.defaults.baseURL = 'http://localhost:8080/';
export default  function getAxios() {
    const axiosInstance = axios.create({
        baseURL: 'http://localhost:8080/',
        timeout: 5000
    })
    //请求拦截器
    axiosInstance.interceptors.request.use(
        config=>{
            if (localStorage.getItem('token')){
                config.headers.token=localStorage.getItem('token');
            }
            return config;
        },
        error => {
            return Promise.reject(error);
        }
    )
    return {axiosInstance}
}
