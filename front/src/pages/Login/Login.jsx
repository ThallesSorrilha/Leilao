import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import "./Login.css";

function Login() {
  const navigate = useNavigate();
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  //
  const [usuario, setUsuario] = useState({email:'',senha:''});
  const handleChange = (e) => {
    setUsuario({...usuario, [e.target.name]:e.target.value});
  }

  const handleLogin = (e) => {
    e.preventDefault();
    navigate("/");
  };

  return (
    <div className="login-container">
      <form className="login-form" onSubmit={handleLogin}>
        <h2>Acessar</h2>
        <div className="input-group">
          <label htmlFor="email">E-mail</label>
          <input
            type="email"
            id="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            required
          />
        </div>
        <div className="input-group">
          <label htmlFor="password">Senha</label>
          <input
            type="password"
            id="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
          />
        </div>
        <button type="submit" className="login-button">
          Acessar
        </button>
        <div className="form-links">
          <Link to="/cadastro">Cadastre-se</Link>
          <Link to="/recuperar-senha">Recuperar Senha</Link>
        </div>
      </form>
    </div>
  );
}

export default Login;
