import React, { useEffect, useState } from "react";
import "../css/EditTest.css";
import TestStep from "./TestStep";
import { Button, Grid } from "@mui/material";
import { useParams } from "react-router-dom";
import LoadingAnimation from "./LoadingAnimation";

function EditTest() {
  const [test, setTest] = useState({});
  const [testSteps, setTestSteps] = useState([]);
  const [elements, setElements] = useState([]);
  const [actions, setActions] = useState([]);
  const {testId} = useParams();
  const [loading, setLoading] = useState(true);


  
  useEffect(() => {
    Promise.all([
      fetch('http://localhost:8080/api/tests/' + testId)
        .then((response) => response.json())
        .then((data) => {
          setTest(data);
          setTestSteps(data.testSteps);
        })
        .catch((error) => {
          console.error("Error fetching user test:", error);
        }),
      fetch('http://localhost:8080/api/actions')
        .then((response) => response.json())
        .then((data) => {
          setActions(data);
        })
        .catch((error) => {
          console.error("Error fetching actions:", error);
        }),
      fetch('http://localhost:8080/api/elements/64e50cbd5d64172dd2e1397e')
        .then((response) => response.json())
        .then((data) => {
          setElements(data);
        })
        .catch((error) => {
          console.error("Error fetching elements:", error);
        })
    ]).then(() => {
      // All fetch requests are completed, so set loading to false
      setLoading(false);
    });
  }, [testId]);

  useEffect(() => {
    console.log("Updated testSteps:", testSteps);
    console.log("updated test: ",test)
  }, [testSteps,test]);
  
  

  const handleDeleteTestStep = (index) => {
    const updatedTestSteps = [...testSteps];
    updatedTestSteps.splice(index, 1);
    for (let i = 0; i < updatedTestSteps.length; i++) {
      updatedTestSteps[i].testStepNumber = i + 1;
    } 
    setTestSteps(updatedTestSteps);
  };

  const handleNewTestStep = () => {
    const newTestStep = {
      "actionId":null,
      "elementId":null,
      "testId":testId,
      "testStepDescription":"",
      "testStepNumber": testSteps.length + 1,
      value:""
    }

    setTestSteps([...testSteps, newTestStep]);
  };



  const handleStepChange = (index, updatedStep) => {
    // Create a copy of the testSteps array
    const updatedTestSteps = [...testSteps];
    
    // Update the test step at the specified index
    updatedTestSteps[index] = updatedStep;
  
    // Update the testSteps state with the modified array
    setTestSteps(updatedTestSteps);
    console.log('Updated testSteps:', updatedTestSteps);
  };
  
  const handleSave = () => {
    const updatedTest = {
      ...test,
      testSteps: testSteps, // Update the testSteps property
    };
  
    fetch('http://localhost:8080/api/update/test', {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(updatedTest),
    })
      .then((response) => response.json())
      .then((data) => {
        console.log("Test successfully updated:", data);
      })
      .catch((error) => {
        console.error("Error updating data:", error);
      });
  }
  

  const moveStepUp = (index) => {
    if (index > 0) {
      const updatedTestSteps = [...testSteps];
      const temp = updatedTestSteps[index];
      updatedTestSteps[index] = updatedTestSteps[index - 1];
      updatedTestSteps[index - 1] = temp;
      setTestSteps(updatedTestSteps);
    }
  };

  const moveStepDown = (index) => {
    if (index < testSteps.length - 1) {
      const updatedTestSteps = [...testSteps];
      const temp = updatedTestSteps[index];
      updatedTestSteps[index] = updatedTestSteps[index + 1];
      updatedTestSteps[index + 1] = temp;
      setTestSteps(updatedTestSteps);
    }
  };

  return (
    <>
      {loading ? (
        <LoadingAnimation />
      ) : (
        <div className="page">
          <div className="left-section">Left Section</div>
          <div className="middle-section">
            <Grid container spacing={2}>
              <Grid item xs={12}>
                <h1>{test.testName}</h1>
              </Grid>
              {testSteps.map((step, index) => (
                <Grid item xs={12} key={index}>
                  <TestStep
                    index={index}
                    step={step}
                    moveStepUp={moveStepUp}
                    moveStepDown={moveStepDown}
                    actionId={step.actionId}
                    elementId={step.elementId}
                    value={step.value}
                    description={step.testStepDescription}
                    actions={actions}
                    elements={elements}
                    onDeleteTestStep={handleDeleteTestStep}
                    onChange={handleStepChange}
                  />
                </Grid>
              ))}
            </Grid>
          </div>
          <div className="right-section">             
            <Button
              variant="contained"
              color="success"
              onClick={handleSave}
            >
              Save
            </Button>
            <Button
              variant="contained"
              color="success"
              onClick={handleNewTestStep}
            >
              Add New Step
            </Button>
            <Button
              variant="contained"
              color="warning"
            >
              Run Test
            </Button>
            </div>
        </div>
      )}
    </>
  );
  
  
}

export default EditTest;

