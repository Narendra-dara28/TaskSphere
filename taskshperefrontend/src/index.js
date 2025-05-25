import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
import { createBrowserRouter, RouterProvider } from 'react-router-dom';
import Login from './components/Login';
import DashBoard from './components/DashBoard';
import CreateProject from './components/CreateProject';
import EditProject from './components/EditProject';
import CreateTask from './components/CreateTask';
import EditTask from './components/EditTask';
import PersonDashboard from './components/PersonDashboard';

const allRoutes = new createBrowserRouter([
  {path : '/', element : <App/>},
  {path : '/login', element : <Login/>},
  {path:'/dashboard', element : <DashBoard/>},
  {path : '/createProject', element : <CreateProject/>},
  {path : '/editProject', element : <EditProject/>},
  {path : '/createTask', element : <CreateTask/>},
  {path : '/editTask', element : <EditTask/>},
  {path : '/personDashboard', element : <PersonDashboard/>}
])

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <RouterProvider router={allRoutes}/>
  </React.StrictMode>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
