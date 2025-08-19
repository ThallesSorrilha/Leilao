import { useState, useRef } from "react";
import { Link, useNavigate } from "react-router-dom";
import { InputText } from "primereact/inputtext";
import { Password } from "primereact/password";
import { Button } from "primereact/button";
import { Toast } from "primereact/toast";
import AutenticacaoService from "../../services/AutenticacaoService";
import "./LoginFrank.css";

function Login() {
  const autenticacaoService = new AutenticacaoService();
  const [usuario, setUsuario] = useState({ email: "", senha: "" });
  const navigate = useNavigate();
  const toast = useRef(null);

  const handleChange = (e) => {
    setUsuario({ ...usuario, [e.target.name]: e.target.value });
  };

  const login = async (e) => {
    e.preventDefault();
    try {
      const resposta = await autenticacaoService.login(usuario);
      console.log("Resposta da API:", resposta);
      if (resposta.status === 200 && resposta.data && resposta.data.token) {
        localStorage.setItem("usuario", JSON.stringify(resposta.data));
        toast.current.show({
          severity: "success",
          summary: "Sucesso",
          detail: "Login realizado com sucesso!",
          life: 3000,
        });
        navigate("/");
      } else {
        const errorMessage =
          resposta.data?.mensagem ||
          "Credenciais inválidas. Verifique seu e-mail e senha.";
        toast.current.show({
          severity: "error",
          summary: "Erro de Login",
          detail: errorMessage,
          life: 3000,
        });
      }
    } catch (error) {
      console.error("Erro ao fazer login (catch):", error);
      const errorMessage =
        error.response?.data?.mensagem ||
        "Ocorreu um erro ao tentar fazer login. Verifique sua conexão ou tente novamente mais tarde.";
      toast.current.show({
        severity: "error",
        summary: "Erro",
        detail: errorMessage,
        life: 5000,
      });
    }
  };

  return (
    <>
      <Toast ref={toast} position="top-right" />
      <div className="container">
        <label>E-mail</label>
        <InputText value={usuario.email} name="email" onChange={handleChange} />
        <label>Senha</label>
        <Password value={usuario.senha} name="senha" onChange={handleChange} />
        <br />
        <Button label="Entrar" onClick={login} />
      </div>
    </>
  );
}

export default Login;
