import axios from "axios";
import { useEffect, useState } from "react";
import DatePicker from "react-datepicker";
import { useLocation, useNavigate } from "react-router-dom";
import "react-datepicker/dist/react-datepicker.css";

export default function EditProject(){
    const [description, setDescription] = useState("");
    const [status, setStatus] = useState("");
    const [title, setTitle] = useState("");
    const [deadLine, setDeadLine] = useState();
    const [id , setId] = useState();
    const navigate = useNavigate();

    const location = useLocation();
    const [pProject, setPProject] = useState(null);
    useEffect(()=>{
        if(location.state && location.state.project){
            const p = location.state.project;
            setDeadLine(p.deadLine);
            setDescription(p.description);
            setStatus(p.status);
            setId(p.id);
            setTitle(p.title);
            setPProject(location.state.project);
        }else{
            console.error("no project found")
        }
    }, [pProject])

    console.log(pProject);
    // setDeadLine(prevProject.deadLine);
    // setDescription(prevProject.description);
    // setStatus(prevProject.status);
    // setTitle(prevProject.title);

    const handleSubmit = () => {
      if (deadLine != null) {
        const formattedDeadLine = new Date(deadLine)
          .toLocaleDateString("en-GB", {
            day: "2-digit",
            month: "long",
            year: "numeric",
          })
          .replace(/ /g, "-");
        const project = {
          id,
          title,
          status,
          deadLine: formattedDeadLine,
          description,
        };
        axios
          .post("http://localhost:9090/project/update", project)
          .then((res) => console.log(res.data))
          .catch((err) => console.log(err));
  
        console.log(project);
        navigate("/dashboard");
      }
    };
  
    return (
      <>
        <h1>Edit Project</h1>
        Title :{" "}
        <input
          type="text"
          value={title}
          required
          placeholder="Enter Title"
          onChange={(e) => setTitle(e.target.value)}
        />
        <br />
        Description :{" "}
        <input
          type="text"
          value={description}
          required
          placeholder="Enter description"
          onChange={(e) => setDescription(e.target.value)}
        />
        <br />
        <div className="p-4">
          <label className="block mb-2 text-lg font-medium">
            Select Date & Time:
          </label>
          <DatePicker
            selected={deadLine}
            onChange={(date) => setDeadLine(date)}
            showTimeSelect
            dateFormat="dd-MMMM-yyyy h:mm aa"
            className="border rounded p-2 w-full"
          />
        </div>
        Status :{" "}
        <input
          type="text"
          placeholder="Enter status"
          value={status}
          onChange={(e) => setStatus(e.target.value)}
        />
        <br />
        <input type="button" onClick={handleSubmit} value="Update project" />
      </>
    );
}