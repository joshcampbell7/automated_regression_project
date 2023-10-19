import React from "react";
import "../css/EditTest.css";
import TextField from '@mui/material/TextField'
import { Button, Grid, InputLabel, MenuItem, Select } from "@mui/material";

const handleChange = ()=>{
    console.log("test")
}

//test account user id - 64e50cbd5d64172dd2e1397e
const TestStep = ({ index, step,moveStepUp,moveStepDown,actionId,elementId,value,description,actions,elements,onDeleteTestStep,onChange}) => {

  const action = actions.find((a) => a.actionId === actionId);
  const element = elements.find((e) => e.elementId === elementId);

  const handleActionChange = (event) => {
    const newActionName = event.target.value; 
    const selectedAction = actions.find((action) => action.actionName === newActionName);
    if (selectedAction) {
      const newActionId = selectedAction.actionId; 
      const updatedStep = {
        ...step,
        actionId: newActionId,
      };
      onChange(index, updatedStep);
    }
  };

  const handleElementChange = (event) => {
    const newElementName = event.target.value; // 
    const selectedElement = elements.find((element) => element.elementName === newElementName);
    if (selectedElement) {
      const newElementId = selectedElement.elementId;
      const updatedStep = {
        ...step,
        elementId: newElementId,
      };
      onChange(index, updatedStep);
    }
  };

  const handleDescriptionChange = (event) =>{
    const newDescription = event.target.value
    const updatedStep = {
      ...step,
      testStepDescription: newDescription,
    };
    onChange(index, updatedStep);
  }

  const handleValueChange = (event) =>{
    const newValue = event.target.value
    const updatedStep = {
      ...step,
      value: newValue,
    };
    onChange(index, updatedStep);
  }


    return (
        <div className="test-step">
          <Grid container spacing={2}>
            <Grid item xs={12}>
              <div className="test-step-section-1">
                <span>Step: {index+1}</span>
              </div>
            </Grid>
            <Grid item xs={12} sm={6}>
              <InputLabel id="action-label">Action</InputLabel>
              <Select
                labelId="action-label"
                id="action-select"
                value={action ? action.actionName : ''}
                label="Action"
                onChange={handleActionChange}
                className="action-select"
              >
                {actions.map((action)=>(
                  <MenuItem key={action.actionId} value={action.actionName}>
                    {action.actionName}
                  </MenuItem>
                ))}
              </Select>
            </Grid>
            <Grid item xs={12} sm={6}>
              <InputLabel id="element-label">Element</InputLabel>
              <Select
                labelId="element-label"
                id="element-select"
                value={element ? element.elementName : ''}
                label="Element"
                onChange={handleElementChange}
                className="element-select"
              >
                {elements.map((element)=>(
                  <MenuItem key={element.elementId} value={element.elementName}>
                    {element.elementName}
                  </MenuItem>
                ))}
              </Select>
            </Grid>
            <Grid item xs={12}>
              <TextField
                id="outlined-basic"
                variant="outlined"
                label="Input Value"
                onChange={handleValueChange}
                value={value}
              />
            </Grid>
            <Grid item xs={12}>
              <TextField
                id="outlined-multiline-static"
                label="Description"
                multiline
                rows={4}
                defaultValue="Default Value"
                onChange={handleDescriptionChange}
                value={description}
              />
            </Grid>
            <Grid item xs={12} sm={6}>
              <Button
                variant="contained"
                color="primary"
                onClick={() => moveStepUp(index)}
              >
                Up
              </Button>
            </Grid>
            <Grid item xs={12} sm={6}>
              <Button
                variant="contained"
                color="primary"
                onClick={() => moveStepDown(index)}
              >
                Down
              </Button>
              <Button
                variant="contained"
                color="warning"
                onClick={()=> onDeleteTestStep(index)}
              >
                Delete
              </Button>
            </Grid>
          </Grid>
        </div>
      );
    };

export default TestStep;
