import { RotatingSquare } from  'react-loader-spinner'
import "../css/LoadingAnimations.css";
function LoadingAnimation() {
  return (
        <div className="centered-square-container">
          <RotatingSquare
            height="100"
            width="100"
            color="#4fa94d"
            ariaLabel="rotating-square-loading"
            strokeWidth="4"
            wrapperStyle={{}}
            wrapperClass=""
            visible={true}
          />
          <h1>Loading..</h1>
        </div>
      );
}

export default LoadingAnimation;
