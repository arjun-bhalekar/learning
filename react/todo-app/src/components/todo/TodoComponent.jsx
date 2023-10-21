import { useNavigate, useParams } from "react-router-dom"
import { useAuth } from "./security/AuthContext"
import { createTodoApi, retrieveTodoApi, updateTodoApi } from "./api/TodoApiService"
import { useEffect, useState } from "react"
import { ErrorMessage, Field, Form, Formik } from "formik"
import moment from "moment"

export default function TodoComponent() {

    const { id } = useParams()
    const authContext = useAuth()
    const username = authContext.loggedInUserId
    const navigate = useNavigate()

    const [description, setDescription] = useState('');
    const [targetDate, setTargetDate] = useState('');

    useEffect(() => {
        //console.log('userEffect called')
        retrieveTodo()
    }, [id])

    function retrieveTodo() {
        if (id > 0) {
            retrieveTodoApi(username, id)
                .then(response => {
                    setDescription(response.data.description)
                    setTargetDate(response.data.targetDate)
                })
                .catch(error => console.log(error))
        }
    }

    function saveTodo(values) {
        console.log('saveTodo called')
        //console.log(values)
        const todo = {
            id: id,
            username: username,
            description: values.description,
            targetDate: values.targetDate,
            done: false

        }
        //console.log(todo)
        if (id > 0) {
            updateTodoApi(username, id, todo)
                .then(response => {
                    //console.log(response)
                    navigate('/todos')
                })
                .catch(error => console.log(error))
        }else {
            createTodoApi(username, todo)
            .then( response => navigate('/todos'))
            .catch( error => console.log(error) )
        }
    }

    function validate(values) {
        console.log('validate called')
        //console.log(values)
        let error = {
            // description : 'Enter a valid description',
            // targetDate : 'Enter a valid target date'
        }

        if (values.description.length < 5) {
            error.description = 'Enter atleast 5 characters'
        }
        if (values.targetDate == null || values.targetDate == "" || !moment(values.targetDate).isValid()) {
            error.targetDate = 'Enter a target date'
        }

        return error
    }

    return (
        <div className="container">
            <h2>Update Todo Details</h2>
            <div>
                <Formik initialValues={{ description, targetDate }}
                    enableReinitialize={true}
                    onSubmit={saveTodo}
                    validate={validate} validateOnChange={false} validateOnBlur={false}>

                    {
                        (props) => (
                            <Form>
                                <ErrorMessage name="description" component="div" className="alert alert-warning" />
                                <ErrorMessage name="targetDate" component="div" className="alert alert-warning" />

                                <fieldset className="form-group">
                                    <label>Description</label>
                                    <Field className="form-control" type="text" name="description" />
                                </fieldset>

                                <fieldset className="form-group">
                                    <label>Target Date</label>
                                    <Field className="form-control" type="date" name="targetDate" />
                                </fieldset>
                                <div>
                                    <button className="btn btn-success m-3" type="submit">Save</button>
                                </div>
                            </Form>
                        )
                    }
                </Formik>

            </div>

        </div>
    )
}