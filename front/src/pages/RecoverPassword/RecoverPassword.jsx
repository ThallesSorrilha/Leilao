import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import "./RecoverPassword.css";

function RecoverPassword() {
  const navigate = useNavigate();
  const [email, setEmail] = useState("");

  const handleRecoverPassword = (e) => {
    e.preventDefault();
    navigate("/inserir-codigo");
    console.log("Password recovery requested for:", email);
  };

  return (
    <div className="recover-container">
      <form className="recover-form" onSubmit={handleRecoverPassword}>
        <h2>Recuperar Senha</h2>
        <p>Insira seu e-mail para receber um link de recuperação.</p>
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
        <div className="button-group">
          <Link to="/" className="cancel-button">
            Cancelar
          </Link>
          <button type="submit" className="recover-button">
            Recuperar Senha
          </button>
        </div>
      </form>
    </div>
  );
}

export default RecoverPassword;
