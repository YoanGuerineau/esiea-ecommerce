import { Outlet } from 'react-router-dom';
import './App.css';
import Header from './components/Header';
import { useEffect, useState } from 'react';

function App() {

  const [toLogout, setToLogout] = useState(false);

  // useEffect(() => {
  //   sessionStorage.getItem('token') ? setToLogout(true) : setToLogout(false);
  // }, []);

  return (
    <div className="App">
      <Header toLogout={toLogout}/>
      <Outlet/>
    </div>
  );
}

export default App;
