import Typography from '@mui/material/Typography';
import { TypographyProps } from '@mui/material/Typography';
import Link from '@mui/material/Link';


interface CopyrightProps extends TypographyProps {}

export default function Copyright(props: CopyrightProps) {
  return (
    <Typography variant="body2" color="text.secondary" align="center" {...props}>
      {'Copyright © '}
      <Link color="inherit" href="https://www.fatecsdp.edu.br/">
        ControlU
      </Link>{' '}
      {new Date().getFullYear()}
      {'.'}
    </Typography>
  );
}