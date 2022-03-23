import logo from './logo.svg';
import './App.css';
import SockJsClient from 'react-stomp';
import React, { useState, useEffect } from "react";
import { isCompositeComponent } from 'react-dom/test-utils';
import {
  useQuery,
  useMutation,
  useQueryClient,
  QueryClient,
  QueryClientProvider,
} from 'react-query'
import { ReactQueryDevtools } from 'react-query/devtools'
import axios from "axios";

function App() {

  const queryClient = new QueryClient()
  return (
    <QueryClientProvider client={queryClient}>
      <Example />
    </QueryClientProvider>
  );

  function toDate(timestamp) {
    if(isNaN(timestamp))
      return "";
    var date = new Date(+timestamp);
    return date.toUTCString();
  }

  function Example() {
    const { isLoading, error, data, isFetching } = useQuery("repoData", () =>
      axios.get(
        "http://localhost:8081/connectors/"
      ).then((res) => res.data)
    );
  
    if (isLoading) return "Loading...";
  
    if (error) return "An error has occurred: " + error.message;
  
    const items = data.map((e) =>
    <tr>
    <td>{e.name}</td>
    <td>{e.state}</td>
    <td>{e.type}</td>
    <td>{e.timestamp_column_name}</td>
    <td>{toDate(e.timestamp_offset)}</td>
    <td>{e.incrementing_column_name}</td>
    <td>{e.incrementing_offset}</td>
    <td>{e.tasks.map((t) => <div>{t.id} : {t.state}</div>)}</td>
    </tr>);

    return (
 
      <div>
      <table>
        <tbody>
       {items}
       </tbody>
        </table>
          {isFetching ? "Updating..." : ""}
        <ReactQueryDevtools initialIsOpen />
      </div>
    );
  }

  // const [userData, setUserData] = useState({});

  // useEffect(() => {
  //   getGitHubUserWithFetch();
  // }, []);

  // const getGitHubUserWithFetch = async () => {
  //   const response = await fetch("http://localhost:8081/status/");
  //   const jsonData = await response.json();
  //   console.log(jsonData);
  //   setUserData(jsonData);
  // };

  // return (
  //   <div className="App">
  //     <header className="App-header">
  //       <img src={logo} className="App-logo" alt="logo" />
  //         <div>
  //           {/* <SockJsClient url='http://localhost:8081/gs-guide-websocket' topics={['/topic/test']}
  //               onMessage={(msg) => { console.log(msg); }}
  //               //ref={ (client) => { this.clientRef = client }} 
  //               /> */}
  //         </div>

  //         <div></div>

  //     </header>
  //   </div>
  // );

  
}

export default App;
