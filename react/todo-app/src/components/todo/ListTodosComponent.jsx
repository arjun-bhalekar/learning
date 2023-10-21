import { useEffect, useState } from "react";
import { deleteTodoApi, retrieveAllTodosForUsernameApi } from "./api/TodoApiService";
import { useAuth } from "./security/AuthContext";
import { useNavigate } from "react-router-dom";

function ListTodosComponent() {

    //const authContext = useContext(AuthContext)
    //console.log('authContext.number : '+authContext.number)

    //const today = new Date();
    //const targetDate = new Date(today.getFullYear() + 12, today.getMonth(), today.getDay())

    const [todos, setTodos] = useState([])
    const [message, setMessage] = useState(null)
    const navigate = useNavigate();
    
    const authContext = useAuth()
    //const isAuthenticated = authContext.isAuthenticated
    const loggedInUserId = authContext.loggedInUserId

    // const todos = [
    //                 { id: 1, description: 'Learn AWS', done: false, targetDate: targetDate},
    //                 { id: 2, description: 'Learn Full Stack Dev', done: false, targetDate: targetDate },
    //                 { id: 3, description: 'Learn DevOps', done: false, targetDate: targetDate }
    //             ]


    //useEffect - this hook tells React that your component need to do something after render

    useEffect(() => { 
        console.log('userEffect called')
        refreshTodos() 
    }, [])

    function refreshTodos() {
        retrieveAllTodosForUsernameApi(loggedInUserId)
            .then((response) => {
                //console.log(response)
                setTodos(response.data)
            })
            .catch(error => console.log(error))
    }

    function deleteTodo(id) {
        console.log('delete clicked ' + id)
        if (window.confirm("Are you sure, you want to delete this Todo ?")) {
            deleteTodoApi(loggedInUserId, id)
                .then(
                    () => {
                        setMessage(`Delete of Todo with id ${id} successful`)
                        refreshTodos()
                    }
                )
        }
    }

    function updateTodo(id){
        console.log('update clicked '+ id)
        navigate(`/todo/${id}`) 
    }

    function addTodo(){
        navigate('/todo/-1')
    }

    return (
        <div className="container">
            <h1>Things you want To Do !</h1>
            {message && <div className="alert alert-warning">{message}</div> }
            
            <div>
                <table className='table'>
                    <thead>
                        <tr>
                            {/* <th>Id</th> */}
                            <th>Description</th>
                            <th>Is Done?</th>
                            <th>Target Date</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            todos.map(todo => (
                                <tr key={todo.id}>
                                    {/* <td>{todo.id}</td> */}
                                    <td>{todo.description}</td>
                                    <td>{todo.done.toString()}</td>
                                    {/* <td>{todo.targetDate.toDateString()}</td> */}
                                    <td>{todo.targetDate.toString()}</td>
                                    <td> 
                                        <button className="btn btn-success m-1" onClick={ () => updateTodo(todo.id)} >Update</button>
                                        <button className="btn btn-warning m-1" onClick={ () => deleteTodo(todo.id)} >Delete</button>
                                    </td>
                                    
                                </tr>
                            )
                            )
                        }

                    </tbody>

                </table>
            </div>

            <button className="btn btn-success m-2" onClick={addTodo}>Add Todo</button>
        </div>
    )
}

export default ListTodosComponent 