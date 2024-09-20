import * as React from 'react';
import Typography from '@mui/material/Typography';
import Title from './Title';
import { Box, Grid, Paper } from '@mui/material';
import { DashboardProps } from '../interface/DashBoardProps';

export const CardDashboard: React.FC<DashboardProps> = ({ titulo, subtitulo, quantidade, quantidadeOposto }) => {
  return (
    <Grid item xs={12} md={4} lg={3}>
      <Paper
        sx={{
          p: 2,
          display: 'flex',
          flexDirection: 'column',
          justifyContent: 'center',
          alignItems: 'center',
          height: 200,
          width: 200
        }}
      >
        <React.Fragment>
          <Title>{titulo}</Title>
          <Typography component="p" color="text.secondary" sx={{ marginBottom: 1 }}>{subtitulo}</Typography>

          <Box sx={{ display: 'flex', justifyContent: 'center', alignItems: 'center', width: '100%', flexWrap: 'wrap', gap: 3}}>
            <Box sx={{ display: 'flex', alignItems: 'center' }}>
              <Box
                sx={{
                  width: 15,
                  height: 15,
                  borderRadius: '50%',
                  backgroundColor: 'green',
                  mr: 1
                }}
              />
              <Typography component="p" variant="h4">{quantidade}</Typography>
            </Box>

            <Box sx={{ display: 'flex', alignItems: 'center' }}>
              <Box
                sx={{
                  width: 15,
                  height: 15,
                  borderRadius: '50%',
                  backgroundColor: 'red',
                  mr: 1
                }}
              />
              <Typography component="p" variant="h4">{quantidadeOposto}</Typography>
            </Box>
          </Box>
        </React.Fragment>
      </Paper>
    </Grid>
  );
}
