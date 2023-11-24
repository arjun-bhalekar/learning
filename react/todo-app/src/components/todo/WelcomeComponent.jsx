import { useState } from "react";
import { Link, useParams } from "react-router-dom";
import { retrieveHelloWorldPathVariable } from "./api/HelloWorldApiService";
import { useAuth } from "./security/AuthContext";

function WelcomeComponent() {

    const { username } = useParams(); //params.username

    const authContext = useAuth()

    //console.log('inside welcomeComponent : username :' + username)

    const [hwMessage, setHwMessage] =  useState(null)

    function callHelloWorldRestApi() {

        console.log('called')
        
        //will be using 'axios 'librabry to call Rest APIs  ==> //>npm install axios
        // axios.get("http://localhost:8080/hello-world")
        //     .then( (response) => successResponse(response))
        //     .catch( (error) => errorResponse(error))
        //     .finally( () => console.log('cleanup'))

        // retrieveHelloWorldBean()
        //     .then((response) => successResponse(response))
        //     .catch((error) => errorResponse(error))
        //     .finally(() => console.log('cleanup'))

        //retrieveHelloWorldPathVariable(username, authContext.token)
        retrieveHelloWorldPathVariable(username)
            .then((response) => successResponse(response))
            .catch((error) => errorResponse(error))
            .finally(() => console.log('cleanup'))
    }

    function successResponse(response){
        console.log(response)
        //setHwMessage(response.data)
        setHwMessage(response.data.message)
    }

    function errorResponse(error){
        console.log(error)
    }



    return (
        <div className="WelcomeComponent">

            <h1>Welcome, {username}</h1>
            <div>
                <Link to="/todos">Manage Todos </Link>
            </div>

            <div>
                <button className="btn btn-success m-5" onClick={callHelloWorldRestApi}>Call Hello World Rest API</button>
            </div>
            <div className="text-info">{hwMessage}</div>


        </div>
    )
}

export default WelcomeComponent