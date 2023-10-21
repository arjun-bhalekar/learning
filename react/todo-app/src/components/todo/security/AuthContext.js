import { createContext, useContext, useState } from "react";


//1. Create a Context
export const AuthContext = createContext()


export const useAuth = () => useContext(AuthContext)

//2. Share the created context with other components
export default function AuthProvider({children}) {

    //3. Put some state in the Context
    //const [number, setNumber] =  useState(10)

    // setInterval(() => {
    //     setNumber(number+1)
    // }, 10000);

    const [isAuthenticated, setAuthenticated] =  useState(false)
    const [loggedInUserId, setLoggedInUserId] =  useState(null)

    function login(username, password) {

        if ((username === 'arjun' || username === 'ranga') && password === 'dummy') {
            setAuthenticated(true)
            setLoggedInUserId(username)
            return true
        } else {
            setAuthenticated(false)
            setLoggedInUserId(null)
            return false
        }
    }

    function logout(){
        setAuthenticated(false)
        setLoggedInUserId(null)
    }

    return (
        <AuthContext.Provider value={ {isAuthenticated, login, logout, loggedInUserId} }>
          {children}  
        </AuthContext.Provider>
    )
}