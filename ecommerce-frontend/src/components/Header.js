import { NavLink } from 'react-router-dom';

export default function Header(props) {

	function deleteToken() {
		sessionStorage.removeItem('token');
	}

	return (
		<header className='App-header'>
			<p>ESIEA INTECH | Fullstack project</p>
			<nav>
				{/* { props.toLogout && <NavLink to='/' onClick={deleteToken}>Log out</NavLink> } */}
				<NavLink to='/' onClick={deleteToken}>Log out</NavLink>
			</nav>
		</header>
	)
}