import 'bootstrap/dist/css/bootstrap.css';
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

import Toast from 'react-bootstrap/Toast';
import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';

function App() {

  const queryClient = new QueryClient()
  return (
    <Container fluid>
      <div className="d-flex flex-row justify-content-between">
        <h1 className="display-2">Konnecticator</h1>
        <h2 className="display-4 m-2 align-self-end">Connector Summary</h2>
      </div>
      <QueryClientProvider client={queryClient}>
        <Example />
      </QueryClientProvider>
    </Container>
  );

  function toDate(timestamp) {
    if (isNaN(timestamp))
      return "";
    var date = new Date(+timestamp);
    return date.toLocaleDateString("en-US", {day:"2-digit", month:"2-digit", hour:"2-digit", minute:"2-digit", second:"2-digit"});
  }

  function Example() {
    const { isLoading, error, data, isFetching } = useQuery("repoData", () =>
      axios.get(
        "http://localhost:8081/connectors/"
      ).then((res) => res.data)
    );

    if (isLoading) return (
      <div>Loading...</div>
    );

    if (error) return "An error has occurred: " + error.message;

    const items = data.map((e) =>
      <Row key={e.name}>
        <Col xl={3} md={4} xs={8} className="d-block d-md-block text-truncate border-bottom pt-3 pb-3">{e.name}</Col>
        <Col xl={1} md={1} xs={4} className="d-block d-md-block text-truncate border-bottom pt-3 pb-3">{e.state}</Col>
        <Col xl={2} md={2} xs={0} className="d-none d-md-block text-truncate border-bottom pt-3 pb-3">{e.incrementing_column_name}</Col>
        <Col xl={1} md={1} xs={0} className="d-none d-md-block text-truncate border-bottom pt-3 pb-3">{e.incrementing_offset}</Col>
        <Col xl={2} md={2} xs={0} className="d-none d-md-block text-truncate border-bottom pt-3 pb-3">{e.timestamp_column_name}</Col>
        <Col xl={2} md={2} xs={0} className="d-none d-md-block text-truncate border-bottom pt-3 pb-3">{toDate(e.timestamp_offset)}</Col>
        <Col xl={1} md={0} xs={0} className="d-none d-xl-block text-truncate border-bottom pt-3 pb-3">{e.tasks.map((t) => <div>Id {t.id} : {t.state}</div>)}</Col>
      </Row>);

    return (
      <div>
        <Container fluid className="p-0">
          <Row>
            <Col xl={3} md={4} xs={8} className="d-block d-md-block text-truncate border-2 border-bottom pt-3 pb-3 fs-4">Name</Col>
            <Col xl={1} md={1} xs={4} className="d-block d-md-block text-truncate border-2 border-bottom pt-3 pb-3 fs-4">State</Col>
            <Col xl={3} md={3} xs={0} className="d-none d-md-block text-truncate border-2 border-bottom pt-3 pb-3 fs-4">Increment</Col>
            <Col xl={4} md={4} xs={0} className="d-none d-md-block text-truncate border-2 border-bottom pt-3 pb-3 fs-4">Timestamp</Col>
            <Col xl={1} md={0} xs={0} className="d-none d-xl-block text-truncate border-bottom pt-3 pb-3 fs-4">Tasks</Col>

          </Row>
          {items}
        </Container>
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
