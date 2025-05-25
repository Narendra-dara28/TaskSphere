import logo from './logo.svg';
import './App.css';
import { Link } from 'react-router-dom';

function App() {
  return (
    <>
      <h1>Home Page</h1>
      <Link to='/login'>Go to Login Page</Link>
    </>
  );
}

export default App;
