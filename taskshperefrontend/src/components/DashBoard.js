import axios from "axios";
import { useEffect, useState } from "react"
import { useNavigate } from "react-router-dom";


export default function DashBoard(){

    const [projects, setProjects] = useState([]);
    const [tasks, setTasks] = useState([]);

    var c = 0;

    const navigate = useNavigate();
   // var user ;
    

    useEffect(()=>{
       //user = localStorage.getItem(user);
        axios.get('http://localhost:9090/project')
        .then(res => setProjects(res.data.projects))
        .catch(err => console.log(err))

        axios.get('http://localhost:9090/task')
        .then(res => setTasks(res.data))
        .catch(err => console.log(err))


    }, [])


    const createProject = () =>{

        navigate('/createProject');

    }

    const handleEdit = (p) =>{
        navigate('/editProject', {state : { project : p}});
    }
    const handleDelete = (p) =>{
        const id = p.id;
        console.log("id : ", id);
        
        axios.delete(`http://localhost:9090/project/delete/${id}`)
        .then(res => console.log(res.data))
        .catch(err => console.log(err))

    }

    const createTask = (p) =>{
        navigate('/createTask', {state : {project : p}});
    }

    const editTask = (t) =>{
        navigate('/editTask', {state : { task : t}});
    }

    const deleteTask =(p) =>{
        const id = p.id;
        console.log("id : ", id);
        
        axios.delete(`http://localhost:9090/task/delete/${id}`)
        .then(res => console.log(res.data))
        .catch(err => console.log(err))

    }

    return(<>

    <h2> Projects:</h2>
    {
    projects && 
        <table border='2px solid black'>
            <thead>
            <tr>
                <th>Id</th>
                <th>Title</th>
                <th>Status</th>
                <th>DeadLine</th>
                
                <th>Edit</th>
                <th>Delete</th>
            </tr>
            </thead>
            <tbody>
            {
                
                projects.map((p)=>(
                    <tr key={p.id}>
                        <td>{p.id}</td>
                        <td>{p.title}</td>
                        <td>{p.status}</td>
                        <td>{p.deadLine}</td>
                        <td><button type="button" onClick={() => handleEdit(p)}>Edit</button></td>
                        <td><button type="button" onClick={() => handleDelete(p)}>Delete</button></td>
                    </tr>
                ))
            }
            </tbody>
        </table>
    }
    <h2> Tasks:</h2>
    {
    tasks && 
        <table border='2px solid black'>
            <thead>
            <tr>
                <th>Id</th>
                <th>Person</th>
                <th>ProjectName</th>
                <th>DeadLine</th>
                <th>Priority</th>
                <th>Status</th>
                <th>Create Task</th>
                <th>Edit</th>
                <th>Delete</th>
            </tr>
            </thead>
            <tbody>
            {
                tasks.map((p)=>(
                    <tr key={p.id}>
                        <td>{p.id}</td>
                        <td>{p.person.name}</td>
                        <td>{p.project.title}</td>
                        <td>{p.deadLine}</td>
                        <td>{p.priority}</td>
                        <td>{p.status}</td>
                        <td><button type="button" onClick={() => createTask(p)}>Create Task</button></td>
                        <td><button type="button" onClick={() => editTask(p)}>Edit</button></td>
                        <td><button type="button" onClick={() => deleteTask(p)}>Delete</button></td>
                    </tr>
                ))
            }
            </tbody>
        </table>
        
    }
    <br/>

    <input type="button" onClick={createProject} value="Create new Project"/>

    </>)
}