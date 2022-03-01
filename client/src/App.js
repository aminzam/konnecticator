import logo from './logo.svg';
import './App.css';
import SockJsClient from 'react-stomp';

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
          <div>
            <SockJsClient url='http://localhost:8081/gs-guide-websocket' topics={['/topic/test']}
                onMessage={(msg) => { console.log(msg); }}
                //ref={ (client) => { this.clientRef = client }} 
                />
          </div>
        <p>
          Edit <code>src/App.js</code> and save to reload!
        </p>
        <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn React
        </a>
      </header>
    </div>
  );
}

export default App;
