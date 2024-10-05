import { SelectChangeEvent } from "@mui/material";

export interface SelectProps {
  value: number | string;
  onChange: (event: SelectChangeEvent<string | number>, child: React.ReactNode) => void; // Alterado para SelectChangeEvent
  isRelatorio: boolean
}