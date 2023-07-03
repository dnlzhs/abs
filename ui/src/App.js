import React, { useEffect, useState } from 'react';
import './App.css';

const App = () => {
  const [aaa, setGroups] = useState([]);
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    setLoading(true);

    fetch('api/go')
        .then(response => response.text())
        .then(data => {
          console.log(data)
          setGroups(data);
          setLoading(false);
        })
  }, []);

  if (loading) {
    return <p>Loading...</p>;
  }


  return (
      <div className="App">
        <header className="App-header">

          <div>
            my dynamic result:

            {aaa}

          </div>
        </header>

      </div>
  );
}

export default App;
