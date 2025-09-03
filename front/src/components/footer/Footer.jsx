import './Footer.css';

const Footer = () => {
  return (
    <footer className="footer">
      <div className="footer-container">
        <div className="footer-info">
          <h3>Leilão Negócio Fechado</h3>
          <p>&copy; {new Date().getFullYear()} Todos os direitos reservados.</p>
        </div>
        <div className="footer-links">
          <h4>Links Úteis</h4>
          <ul>
            <li>Política de Privacidade</li>
            <li>Termos de Serviço</li>
            <li>FAQ</li>
          </ul>
        </div>
        <div className="footer-social">
          <h4>Siga-nos</h4>
          <ul>
            <li><i className="pi pi-facebook"></i></li>
            <li><i className="pi pi-twitter"></i></li>
            <li><i className="pi pi-instagram"></i></li>
          </ul>
        </div>
      </div>
    </footer>
  );
};

export default Footer;
