import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import "./NewRegistration.css";

function NewRegistration() {
  const navigate = useNavigate();
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");

  const handleRegistration = (e) => {
    e.preventDefault(); // ?
    navigate("/inserir-codigo");
  };

  return (
    <div className="registration-container">
      <form className="registration-form" onSubmit={handleRegistration}>
        <h2>Novo Cadastro</h2>
        <div className="input-group">
          <label htmlFor="name">Nome</label>
          <input
            type="text"
            id="name"
            value={name}
            onChange={(e) => setName(e.target.value)}
            required
          />
        </div>
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
          <button type="submit" className="submit-button">
            Cadastrar
          </button>
        </div>
      </form>
    </div>
  );
}

export default NewRegistration;
