import React from "react";
import "../css/Sidebar.css";
import {
  MdDashboard,
  MdOutbound,
  MdOutlineFindInPage,
  MdOutlineTerminal,
  MdOutlineImportExport,
  MdOutlineBrightnessHigh,
} from "react-icons/md";

function Sidebar() {
  return (
    <div className="sidebar">
      <h1 className="company-name">Automated Testing Tool</h1>
      <ul>
        <li className="sidebar-item">
          <MdDashboard style={{ marginRight: "8px", fontSize: "30px" }} />
          <a href="#">Dashboard</a>
        </li>
        <li className="sidebar-item">
          <MdOutbound style={{ marginRight: "8px", fontSize: "30px" }} />
          <a href="#">Test Suite</a>
        </li>
        <li className="sidebar-item">
          <MdOutlineFindInPage
            style={{ marginRight: "8px", fontSize: "30px" }}
          />
          <a href="#">View Reports</a>
        </li>
        <li className="sidebar-item">
          <MdOutlineTerminal style={{ marginRight: "8px", fontSize: "30px" }} />
          <a href="#">View Element Lookups</a>
        </li>
        <li className="sidebar-item">
          <MdOutlineImportExport
            style={{ marginRight: "8px", fontSize: "30px" }}
          />
          <a href="#">Integrations</a>
        </li>
        <li className="sidebar-item">
          <MdOutlineBrightnessHigh
            style={{ marginRight: "8px", fontSize: "30px" }}
          />
          <a href="#">Settings</a>
        </li>
      </ul>
    </div>
  );
}

export default Sidebar;
