import axios from "axios";
import { useEffect, useState } from "react";
import { useLocation } from "react-router-dom";

export default function PersonDashboard() {
  const [task, setTask] = useState(null);
  const location = useLocation();
  const id = location?.state?.id || localStorage.getItem("personId");

  useEffect(() => {
    if (!id) return;
    axios
      .get(`http://localhost:9090/task/getById/${id}`)
      .then((res) => setTask(res.data))
      .catch((err) => console.log(err));
  }, [id]);

  if (!task) return <div>Loading...</div>;

  return (
    <>
      <h2>Task Details:</h2>
      Task ID: {task.id}<br />
      Assigned To: {task.person?.name}<br />
      Email: {task.person?.email}<br />
      <h3>Projects of the Person:</h3>
      {task.person.projects?.map((p) => (
        <span key={p.id}>
          {p.title} - {p.status}<br />
        </span>
      ))}
      <h3>Task Info:</h3>
      Deadline: {task.deadLine}<br />
      Priority: {task.priority}<br />
      Status: {task.status}
    </>
  );
}
