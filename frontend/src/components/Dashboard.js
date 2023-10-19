import Sidebar from "./Sidebar";
import "../css/Dashboard.css";
function Dashboard() {
  return (
    <div className="dashboard">
      <Sidebar />
      <div className="dashboard-content">
        <h1>Welcome Joshua</h1>
        <div className="sections">
          <div className="section-1">
          <h1>Analytics Graphs will be shown here</h1>
          </div>
          <div className="section-2">
          <h1>Failed to pass test ratio will be shown here</h1>
          </div>
        </div>
        <div className="section-3">
          <h1>Recently made tests will be shown here</h1>
        </div>
      </div>
    </div>
  );
}

export default Dashboard;
