import Header from "../../components/header/Header.jsx";
import Footer from "../../components/footer/Footer.jsx";

const PadraoLayout = ({ children }) => {
  return (
    <>
      {<Header />}
      {children}
      {<Footer />}
    </>
  );
};
export default PadraoLayout;
