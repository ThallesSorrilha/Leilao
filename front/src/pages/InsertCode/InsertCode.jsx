import { useState, useRef } from "react";
import { Link, useNavigate } from "react-router-dom";
import "./InsertCode.css";

function InsertCode() {
  const navigate = useNavigate();
  const [digits, setDigits] = useState(["", "", "", "", "", ""]);
  const inputRefs = useRef([]);

  const handleSubmit = (e) => {
    e.preventDefault();
    const code = digits.join("");

    if (code.length === 6) {
      console.log("Código de 6 dígitos enviado:", code);
      navigate("/alterar-senha");
    } else {
      alert("Por favor, insira o código de 6 dígitos completo.");
    }
  };

  const handleChange = (index, value) => {
    const numericValue = value.replace(/[^0-9]/g, "");

    if (numericValue) {
      const newDigits = [...digits];
      newDigits[index] = numericValue;
      setDigits(newDigits);

      if (index < 5 && numericValue !== "") {
        inputRefs.current[index + 1].focus();
      }
    } else {
      const newDigits = [...digits];
      newDigits[index] = "";
      setDigits(newDigits);
    }
  };

  const handleKeyDown = (index, e) => {
    if (e.key === "Backspace" && digits[index] === "" && index > 0) {
      inputRefs.current[index - 1].focus();
    }
  };

  return (
    <div className="insert-code-container">
      <form className="insert-code-form" onSubmit={handleSubmit}>
        <h2>Inserir Código</h2>
        <p className="description">
          Um código de 6 dígitos<br />
          foi enviado para o seu e-mail.
        </p>
        <div className="code-input-group">
          {digits.map((digit, index) => (
            <input
              key={index}
              type="text"
              className="code-input"
              value={digit}
              onChange={(e) => handleChange(index, e.target.value)}
              onKeyDown={(e) => handleKeyDown(index, e)}
              maxLength={1}
              required
              ref={(el) => (inputRefs.current[index] = el)}
            />
          ))}
        </div>
        <div className="button-group">
          <Link to="/" className="cancel-button">
            Cancelar
          </Link>
          <button type="submit" className="insert-button">
            Confirmar
          </button>
        </div>
      </form>
    </div>
  );
}

export default InsertCode;