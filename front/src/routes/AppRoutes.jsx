import { BrowserRouter, Routes, Route } from "react-router-dom";
import Login from "../pages/Login/Login.jsx";
import NewRegistration from "../pages/NewRegistration/NewRegistration";
import RecoverPassword from "../pages/RecoverPassword/RecoverPassword";
import ChangePassword from "../pages/ChangePassword/ChangePassword";
import InsertCode from "../pages/InsertCode/InsertCode.jsx";
import Perfil from "../pages/Perfil/Perfil.jsx";
import CategoriaCRUD from "../pages/Categoria/Categoria.jsx";
import RotaPrivadaLayout from "../components/layout/RotaPrivadaLayout.jsx";
import PadraoLayout from "../components/layout/PadraoLayout.jsx";

const AppRoutes = () => {
  return (
    <BrowserRouter>
      <Routes>
        <Route element={<RotaPrivadaLayout />}>
          <Route
            path="/"
            element={<PadraoLayout>{/*<Home />*/}</PadraoLayout>}
          />
          <Route
            path="/perfil"
            element={
              <PadraoLayout>
                <Perfil />
              </PadraoLayout>
            }
          />
          <Route
            path="/feedback"
            element={<PadraoLayout>{/*<Feedback />*/}</PadraoLayout>}
          />
          <Route
            path="/imagem"
            element={<PadraoLayout>{/*<Imagem />*/}</PadraoLayout>}
          />
          <Route
            path="/lance"
            element={<PadraoLayout>{/*<Lance />*/}</PadraoLayout>}
          />
          <Route
            path="/leilao"
            element={<PadraoLayout>{/*<Leilao />*/}</PadraoLayout>}
          />
          <Route
            path="/pagamento"
            element={<PadraoLayout>{/*<Pagamento />*/}</PadraoLayout>}
          />
          <Route
            path="/pessoa"
            element={<PadraoLayout>{/*<Pessoa />*/}</PadraoLayout>}
          />
        </Route>

        {/**/}
        <Route
          path="/categoria"
          element={<PadraoLayout>{<CategoriaCRUD />}</PadraoLayout>}
        />

        <Route
          path="/login"
          element={
            <PadraoLayout>
              <Login />
            </PadraoLayout>
          }
        />
        <Route
          path="/cadastro"
          element={
            <PadraoLayout>
              <NewRegistration />
            </PadraoLayout>
          }
        />
        <Route
          path="/recuperar-senha"
          element={
            <PadraoLayout>
              <RecoverPassword />
            </PadraoLayout>
          }
        />
        <Route
          path="/alterar-senha"
          element={
            <PadraoLayout>
              <ChangePassword />
            </PadraoLayout>
          }
        />
        <Route
          path="/inserir-codigo"
          element={
            <PadraoLayout>
              <InsertCode />
            </PadraoLayout>
          }
        />
      </Routes>
    </BrowserRouter>
  );
};

export default AppRoutes;
