import * as React from 'react';
import ListItemButton from '@mui/material/ListItemButton';
import ListItemIcon from '@mui/material/ListItemIcon';
import ListItemText from '@mui/material/ListItemText';
import { SvgIconProps } from '@mui/material/SvgIcon';

import { useNavigate } from 'react-router-dom';

interface ListItemProps {
  icon: React.ElementType<SvgIconProps>;
  primary: string;
  pathRouter: string;
}

const ListItem: React.FC<ListItemProps> = ({ icon: Icon, primary, pathRouter }) => {
  const navigate = useNavigate();

  const handleClick = () => {
    navigate(pathRouter);
  };

  return (
    <ListItemButton onClick={handleClick}>
      <ListItemIcon>
        <Icon />
      </ListItemIcon>
      <ListItemText primary={primary} />
    </ListItemButton>
  );
};

export default ListItem;
