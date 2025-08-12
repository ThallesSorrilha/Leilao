import { BrowserRouter, Routes, Route } from "react-router-dom";
import Login from "../pages/Login/Login.jsx";
import NewRegistration from "../pages/NewRegistration/NewRegistration";
import RecoverPassword from "../pages/RecoverPassword/RecoverPassword";
import ChangePassword from "../pages/ChangePassword/ChangePassword";
import InsertCode from "../pages/InsertCode/InsertCode.jsx";

const AppRoutes = () => {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/login" element={<Login />} />
        <Route path="/cadastro" element={<NewRegistration />} />
        <Route path="/recuperar-senha" element={<RecoverPassword />} />
        <Route path="/alterar-senha" element={<ChangePassword />} />
        <Route path="/inserir-codigo" element={<InsertCode />} />
      </Routes>
    </BrowserRouter>
  );
};

export default AppRoutes;
