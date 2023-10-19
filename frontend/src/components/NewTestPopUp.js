import React, { useState } from "react";
import { Dialog, DialogTitle, DialogContent, DialogActions, TextField, Button } from "@mui/material";

const NewTestPopUp = ({ open, onClose, onCreate }) => {
  const [testName, setTestName] = useState("");
  const [testDescription, setTestDescription] = useState("");

  const handleCreateTest = () => {
    // Perform any validation or logic you need and then call the onCreate function
    if(testName.trim()===""){
        alert("Test name cannot be empty")
    }
    if(testDescription.trim()===""){
      alert("Test description cannot be empty")
  }
    else{
        onCreate(testName,testDescription);
        setTestName(""); // Clear the input field
        setTestDescription("")
    }
  };

  return (
    <Dialog open={open} onClose={onClose}>
      <DialogTitle>Enter Test Name</DialogTitle>
      <DialogContent>
        <TextField
          label="Test Name"
          variant="outlined"
          value={testName}
          onChange={(e) => setTestName(e.target.value)}
        />
      </DialogContent>
      <DialogContent>
        <TextField
          label="Test Description"
          variant="outlined"
          value={testDescription}
          multiline
          rows={4}
          onChange={(e) => setTestDescription(e.target.value)}
        />
      </DialogContent>
      <DialogActions>
        <Button onClick={onClose} color="primary">
          Cancel
        </Button>
        <Button onClick={handleCreateTest} color="primary">
          Create
        </Button>
      </DialogActions>
    </Dialog>
  );
};

export default NewTestPopUp;
