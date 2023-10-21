import './TodoApp.css'
import { BrowserRouter, Navigate, Route, Routes } from 'react-router-dom'
import LogoutComponent from './LogoutComponent'
//import FooterComponent from './FooterComponent'
import HeaderComponent from './HeaderComponent'
import ErrorComponent from './ErrorComponent'
import ListTodosComponent from './ListTodosComponent'
import WelcomeComponent from './WelcomeComponent'
import LoginComponent from './LoginComponent'
import AuthProvider, { useAuth } from './security/AuthContext'
import TodoComponent from './TodoComponent'

function AuthenticateRoute({ children }) {

    const authContext = useAuth()

    if (authContext.isAuthenticated) {
        return children
    } else {
        return <Navigate to="/" />
    }

}

export default function TodoApp() {



    return (
        <div className="TodoApp">
            <AuthProvider>
                <BrowserRouter>
                    <HeaderComponent />
                    <Routes>
                        <Route path='/' element={<LoginComponent />} />
                        <Route path='/login' element={< LoginComponent />} />

                        <Route path='/welcome/:username' element={
                            <AuthenticateRoute>
                                <WelcomeComponent />
                            </AuthenticateRoute>

                        } />


                        <Route path='/todos' element={
                            <AuthenticateRoute>
                                <ListTodosComponent />
                            </AuthenticateRoute>

                        } />

                        <Route path='/todo/:id' element={
                            <AuthenticateRoute>
                                <TodoComponent />
                            </AuthenticateRoute>

                        } />

                        <Route path='/logout' element={
                            <AuthenticateRoute><LogoutComponent /></AuthenticateRoute>

                        } />

                        <Route path='*' element={<ErrorComponent />} />
                    </Routes>
                    {/* <FooterComponent /> */}
                </BrowserRouter>
            </AuthProvider>
        </div>
    )
}

