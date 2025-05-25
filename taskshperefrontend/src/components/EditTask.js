


import axios from "axios";
import { use, useEffect, useState } from "react";
import DatePicker from "react-datepicker";
import { useLocation, useNavigate } from "react-router-dom";

export default function EditTask() {
  const [proname, setProname] = useState();
  const [deadLine, setDeadLine] = useState();
  const [priority, setPriority] = useState("");
  const [status, setStatus] = useState("");
  const [pname, setPname] = useState();
  const [personId, setPersonId] = useState("");
  const [projectId, setProjectId] = useState("");
  const [id, setTaskId] = useState('');

  const navigate = useNavigate();

  const location = useLocation();
  const [pProject, setPProject] = useState(null);
  useEffect(() => {
    if (location.state && location.state.task) {
      const p = location.state.task;
      setProname(p.project.title);
      setPname(p.person.name);
      setPersonId(p.person.id);
      setProjectId(p.project.id);
      setDeadLine(new Date(p.deadLine));
      setStatus(p.status);
      setTaskId(p.id);
      setPriority(p.priority);
    } else {
      console.error("no project found");
    }
  }, [pProject]);

  const handleSubmit = () => {
    
    if (deadLine != null) {
        const formattedDeadLine = deadLine
        .toLocaleDateString("en-GB", {
          day: "2-digit",
          month: "long",
          year: "numeric",
        })
        .replace(/ /g, "-");
        const task = {
        id,
        personId,
        projectId,
        deadLine: formattedDeadLine,
        priority,
        status,
      }
      console.log(task);
    axios
      .post("http://localhost:9090/task/update", task)
      .then((res) => console.log(res.data))
      .catch((err) => console.log(err));
    }

    navigate('/dashboard')
    
  };

  return (
    <>
      <h1>Edit Task for {proname}</h1>
      {/* <span key={person}>{person}</span> */}
     Person : <input type = "text"
                        value={pname}
                        readOnly
                        onChange={(e)=> setPname(e.target.value)}/><br/>
    Project : <input type="text"
                        value={proname}
                        readOnly
                        onChange={(e)=> setProname(e.target.value)}/><br/>
    
      Priority :{" "}
      <input
        type="text"
        value={priority}
        required
        placeholder="Enter proroity"
        onChange={(e) => setPriority(e.target.value)}
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
      <input type="button" onClick={handleSubmit} value="Update Task" />
    </>
  );
}
