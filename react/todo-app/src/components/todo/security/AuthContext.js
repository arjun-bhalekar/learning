import { createContext, useContext, useState } from "react";
import { apiClient } from "../api/ApiClient";
import { executeJwtAuthenticationService } from "../api/AuthenticationApiService";


//1. Create a Context
export const AuthContext = createContext()


export const useAuth = () => useContext(AuthContext)

//2. Share the created context with other components
export default function AuthProvider({ children }) {

    //3. Put some state in the Context
    //const [number, setNumber] =  useState(10)

    // setInterval(() => {
    //     setNumber(number+1)
    // }, 10000);

    const [isAuthenticated, setAuthenticated] = useState(false)
    const [loggedInUserId, setLoggedInUserId] = useState(null)
    const [token, setToken] = useState(null)

    // function login(username, password) {

    //     if ((username === 'arjun' || username === 'ranga') && password === 'dummy') {
    //         setAuthenticated(true)
    //         setLoggedInUserId(username)
    //         return true
    //     } else {
    //         setAuthenticated(false)
    //         setLoggedInUserId(null)
    //         return false
    //     }
    // }

    // async function login(username, password) {

    //     const baToken = 'Basic ' + window.btoa(username + ":" + password)
       
    //     try {
    //         const response = await executeBasicAuthenticationService(baToken)
            
    //         if (response.status == 200) {
    //             setAuthenticated(true)
    //             setLoggedInUserId(username)
    //             setToken(baToken)

    //             apiClient.interceptors.request.use(
    //                 (config) => {
    //                     console.log('intercepting request and adding a token')
    //                     config.headers.Authorization = baToken
    //                     return config
    //                 }
    //             )

    //             return true
    //         } else {
    //             logout()
    //             return false
    //         }
    //     } catch (error) {
    //         logout()
    //         return false
    //     }

    // }

    async function login(username, password) {
      
        try {
            const response = await executeJwtAuthenticationService(username, password)
            
            if (response.status == 200) {
                
                const jwtToken = "Bearer " + response.data.token

                setAuthenticated(true)
                setLoggedInUserId(username)
                setToken(jwtToken)

                apiClient.interceptors.request.use(
                    (config) => {
                        console.log('intercepting request and adding a jwt token')
                        config.headers.Authorization = jwtToken
                        return config
                    }
                )

                return true
            } else {
                logout()
                return false
            }
        } catch (error) {
            logout()
            return false
        }

    }

    function logout() {
        setAuthenticated(false)
        setLoggedInUserId(null)
        setToken(null)
    }

    return (
        <AuthContext.Provider value={{ isAuthenticated, login, logout, loggedInUserId, token }}>
            {children}
        </AuthContext.Provider>
    )
}