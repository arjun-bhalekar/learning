import { apiClient } from "./ApiClient";

// export function retrieveHelloWorldBean(){
//     return axios.get("http://localhost:8080/hello-world-bean")
// }



export const retrieveHelloWorldBean = () => apiClient.get("/hello-world-bean")

export const retrieveHelloWorldPathVariable
    = (username) => apiClient.get(`/hello-world/path-variable/${username}`)

// export const retrieveHelloWorldPathVariable
//     = (username, token) => apiClient.get(`/hello-world/path-variable/${username}`, {
//         headers: {
//             Authorization: token
//         }
//     })

