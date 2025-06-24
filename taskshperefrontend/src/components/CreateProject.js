//  iam make modiification and updating ito the git
import axios from "axios";
import { useState } from "react";
import DatePicker from "react-datepicker";
import { useNavigate } from "react-router-dom";
import "react-datepicker/dist/react-datepicker.css";

export default function CreateProject() {
  const [description, setDescription] = useState("");
  const [status, setStatus] = useState("");
  const [title, setTitle] = useState("");
  const [deadLine, setDeadLine] = useState();

  const navigate = useNavigate();

  const handleSubmit = () => {
    if (deadLine != null) {
      const formattedDeadLine = deadLine
        .toLocaleDateString("en-GB", {
          day: "2-digit",
          month: "long",
          year: "numeric",
        })
        .replace(/ /g, "-");
      const project = {
        title,
        status,
        deadLine: formattedDeadLine,
        description,
      };
      axios
        .post("http://localhost:9090/project", project)
        .then((res) => console.log(res.data))
        .catch((err) => console.log(err));

      console.log(project);
      navigate("/dashboard");
    }
  };

  return (
    <>
      <h1>Create a new Project</h1>
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
      <input type="button" onClick={handleSubmit} value="Create" />
    </>
  );
}

/*

import React, { useState } from "react";
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";

export default function DateTimePickerWithMonthName() {
  const [selectedDate, setSelectedDate] = useState(new Date());

  return (
    <div className="p-4">
      <label className="block mb-2 text-lg font-medium">Select Date & Time:</label>
      <DatePicker
        selected={selectedDate}
        onChange={(date) => setSelectedDate(date)}
        showTimeSelect
        dateFormat="dd-MMMM-yyyy h:mm aa"
        className="border rounded p-2 w-full"
      />
    </div>
  );
}




*/
