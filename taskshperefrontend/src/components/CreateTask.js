import axios from "axios";
import { use, useEffect, useState } from "react";
import DatePicker from "react-datepicker";
import { useLocation, useNavigate } from "react-router-dom";

export default function CreateTask() {
  const [proname, setProname] = useState();
  const [deadLine, setDeadLine] = useState();
  const [priority, setPriority] = useState("");
  const [status, setStatus] = useState("");
  const [pname, setPname] = useState();
  const [personId, setPersonId] = useState("");
  const [projectId, setProjectId] = useState("");

  const navigate = useNavigate();

  const location = useLocation();
  const [pProject, setPProject] = useState(null);
  useEffect(() => {
    if (location.state && location.state.project) {
      const p = location.state.project;
      setProname(p.project.title);
      setPname(p.person.name);
      setPersonId(p.person.id);
      setProjectId(p.project.id);
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
        personId,
        projectId,
        deadLine: formattedDeadLine,
        priority,
        status,
      }
      console.log(task);
    axios
      .post("http://localhost:9090/task", task)
      .then((res) => console.log(res.data))
      .catch((err) => console.log(err));
    }

    navigate('/dashboard')
    
  };

  return (
    <>
      <h1>Create Task for {proname}</h1>
      {/* <span key={person}>{person}</span> */}
      User : {pname} <br />
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
      <input type="button" onClick={handleSubmit} value="Create Task" />
    </>
  );
}
