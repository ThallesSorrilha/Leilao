import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import "./ChangePassword.css";

const validatePassword = (password) => {
  const errors = [];
  if (password.length < 6) {
    errors.push("A senha deve ter no mínimo 6 caracteres.");
  }
  if (!/[A-Z]/.test(password)) {
    errors.push("A senha deve conter pelo menos 1 letra maiúscula.");
  }
  if (!/[a-z]/.test(password)) {
    errors.push("A senha deve conter pelo menos 1 letra minúscula.");
  }
  if (!/[0-9]/.test(password)) {
    errors.push("A senha deve conter pelo menos 1 número.");
  }
  if (!/[!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]+/.test(password)) {
    errors.push("A senha deve conter pelo menos 1 caractere especial.");
  }
  return errors;
};

function ChangePassword() {
  const navigate = useNavigate();
  const [newPassword, setNewPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  const [passwordErrors, setPasswordErrors] = useState([]);
  const [confirmPasswordError, setConfirmPasswordError] = useState("");

  const handleNewPasswordChange = (e) => {
    const password = e.target.value;
    setNewPassword(password);
    setPasswordErrors(validatePassword(password));
    if (confirmPassword && password !== confirmPassword) {
      setConfirmPasswordError("As senhas não coincidem.");
    } else {
      setConfirmPasswordError("");
    }
  };

  const handleConfirmPasswordChange = (e) => {
    const password = e.target.value;
    setConfirmPassword(password);
    if (newPassword !== password) {
      setConfirmPasswordError("As senhas não coincidem.");
    } else {
      setConfirmPasswordError("");
    }
  };

  const handleChangePassword = (e) => {
    e.preventDefault();
    const errors = validatePassword(newPassword);
    if (errors.length > 0) {
      setPasswordErrors(errors);
      return;
    }
    if (newPassword !== confirmPassword) {
      setConfirmPasswordError("As senhas não coincidem.");
      return;
    }

    navigate("/");
  };

  return (
    <div className="change-password-container">
      <form className="change-password-form" onSubmit={handleChangePassword}>
        <h2>Criar nova senha</h2>
        <p>Crie uma senha forte para usar no sistema</p>
        <div className="input-group">
          <label htmlFor="newPassword">Nova Senha</label>
          <input
            type="password"
            id="newPassword"
            value={newPassword}
            onChange={handleNewPasswordChange}
            required
          />
          {passwordErrors.length > 0 && (
            <ul className="error-list">
              {passwordErrors.map((error, index) => (
                <li key={index} className="error-message">
                  {error}
                </li>
              ))}
            </ul>
          )}
        </div>
        <div className="input-group">
          <label htmlFor="confirmPassword">Confirmar Senha</label>
          <input
            type="password"
            id="confirmPassword"
            value={confirmPassword}
            onChange={handleConfirmPasswordChange}
            required
          />
          {confirmPasswordError && (
            <span className="error-message">{confirmPasswordError}</span>
          )}
        </div>
        <div className="button-group">
          <Link to="/" className="cancel-button">
            Cancelar
          </Link>
          <button type="submit" className="change-button">
            Alterar Senha
          </button>
        </div>
      </form>
    </div>
  );
}

export default ChangePassword;
