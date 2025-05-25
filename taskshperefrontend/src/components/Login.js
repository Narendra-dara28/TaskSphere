import axios from "axios";
import { useState } from "react";
import { useNavigate } from "react-router-dom";

export default function Login() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [person, setPerson] = useState({});
  const navigate = useNavigate();

  const [personError, setPersonError] = useState("");

  const handleSubmit = (e) => {
    let user = { email, password };
    axios
      .post("http://localhost:9090/person/login", user)
      .then((res) => {
        console.log("From data base :", res.data);
        setPerson(res.data);
        if (res.data.email && res.data.password) {
          const id = res.data.id;
          console.log(id);
          if (res.data.type === "admin") {
            navigate("/dashboard");
          } else if (res.data.type === "user") {
            localStorage.setItem("personId", id);
            navigate("/personDashboard");
          }
          // localStorage.setItem("user" , user.email);
        } else {
          setPersonError("No such person found with given credentials");
        }
      })
      .catch((err) => console.log(err));
  };

  return (
    <>
      <h1>Login Page</h1>

      <form>
        Email :{" "}
        <input
          type="email"
          placeholder="Enter email"
          onChange={(e) => setEmail(e.target.value)}
          value={email}
        />
        <br />
        Password :{" "}
        <input
          type="password"
          placeholder="Enter your password"
          onChange={(e) => setPassword(e.target.value)}
          value={password}
        />
        <br />
        <input type="button" onClick={handleSubmit} value="Login" />
        <br />
        {personError && <span style={{ color: "red" }}>{personError}</span>}
      </form>
    </>
  );
}
