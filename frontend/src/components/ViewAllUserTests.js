import Sidebar from "./Sidebar";
import "../css/Dashboard.css";
import { Button } from "@mui/material";
import { useEffect, useState } from "react";
import NewTestPopUp from "./NewTestPopUp";
import {Link, useNavigate} from "react-router-dom"
import LoadingAnimation from "./LoadingAnimation";
function ViewUserTests() {
  const [userTests,setUserTests] = useState([])
  const [userId, setUserId] = useState("64e50cbd5d64172dd2e1397e");
  const [openPopUpBox,setPopUpBox] = useState(false)
  const [loading, setLoading] = useState(true); // Add loading state
  const navigate = useNavigate()

  const openNewTestPopUp = ()=>{
    setPopUpBox(true)
  }

  const closeNewTestPopUp = ()=>{
    setPopUpBox(false)
  }

  useEffect(() => {
    fetch('http://localhost:8080/api/user/tests/'+ userId)
    .then((response) => response.json())
    .then((data) => {
      setUserTests(data)
      setLoading(false)
      console.log(userTests)
    })
    .catch((error) => {
      console.error("Error fetching user tests:", error);
      setLoading(false)
    });
  }, []);

  useEffect(() => {
    console.log("User Tests:", userTests);
  }, [userTests]);
  

  const handleCreateTest = (testName,testDescription) => {
    console.log(testDescription,testName)
    fetch("http://localhost:8080/api/add/test", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        "testName":testName,
        "testDescription": testDescription,
        "userId": "64e50cbd5d64172dd2e1397e",
      }),
    })
      .then((response) => {
        if (response.ok) {
          return response.json(); // Assuming the response contains the newly created test data
        } else {
          throw new Error("Failed to create a new test");
        }
      })
      .then((newTest) => {
        newTest.testSteps = []
        console.log("New test created:", newTest);
        navigate(`/edittest/${newTest.testId}`)
      })
      .catch((error) => {
        console.error("Error creating a new test:", error);
      });
  
    closeNewTestPopUp();
  };
    
  return (
    <>
      {loading ? (
        <LoadingAnimation />
      ) : (
        <div className="dashboard">
        <Sidebar />
        <div className="dashboard-content">
          <h1>View all tests</h1>
          <div className="section">
            <Button variant="contained" color="success" onClick={openNewTestPopUp}>Create New Test</Button>
          </div>
          <div className="section-3">
            <h1>Recently made tests will be shown here</h1>
            <ul>
            {userTests.map((test) => (
              <li key={test.testId}>
                <Link to={`/edittest/${test.testId}`}>{test.testName}</Link>
              </li>
            ))}
          </ul>
          </div>
  
          <NewTestPopUp open={openPopUpBox} onClose={closeNewTestPopUp} onCreate={handleCreateTest}/>
        </div>
      </div>
      )}
    </>
  );


}

export default ViewUserTests;
