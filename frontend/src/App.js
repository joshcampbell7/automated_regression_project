import { Routes, Route, BrowserRouter } from "react-router-dom";
import "./App.css";
import Dashboard from "./components/Dashboard";
import ViewUserTests from "./components/ViewAllUserTests";
import EditTest from "./components/EditTest";
import LoadingAnimation from "./components/LoadingAnimation";

function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <Routes>
          <Route path="/dashboard/:userId" element={<Dashboard />} />
          <Route path="/tests" element={<ViewUserTests />} />
          <Route path="/edittest/:testId" element={<EditTest/>} />
          <Route path="/animation" element={<LoadingAnimation/>} />
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
