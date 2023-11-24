import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { useAuth } from "./security/AuthContext";

function LoginComponent() {

    const [username, setUsername] = useState('arjun');
    const [password, setPassword] = useState('');

    const [showErrorMessage, setShowErrorMessage] = useState(false);

    const navigate = useNavigate();

    const authContext = useAuth()

    function handleUsernameChange(event) {
        setUsername(event.target.value)
    }

    function handlePasswordChange(event) {
        setPassword(event.target.value)
    }

    async function handleSubmit() {
        //console.log(username +"---"+password);

        if (await authContext.login(username, password)) {
            //navigate('/welcome/'+username) //this also works
            navigate(`/welcome/${username}`) //using tick
        } else {
            setShowErrorMessage(true)
        }

    }

    return (
        <div className="Login">
            <h2>Todo Management Application !</h2>
            <div className="LoginForm">
                <div>
                    <label>User Name</label>
                    <input type="text" name="username" value={username} onChange={handleUsernameChange} />
                </div>
                <div>
                    <label>Password</label>
                    <input type="password" name="password" value={password} onChange={handlePasswordChange} />
                </div>
                <div>
                    <button type="button" name="login" onClick={handleSubmit}>Login</button>
                </div>
            </div>
           
            {showErrorMessage && <div className='errorMessage'>Authentication failed. Please check your credentials</div>}


        </div>
    )
}

export default LoginComponent